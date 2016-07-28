//package deadLock;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class ProducerAndConsumer {
//
//	private final Lock enqueueLock = new ReentrantLock();
//
//	private final Condition notEmpty = this.enqueueLock.newCondition();
//
//	private final Condition empty = this.enqueueLock.newCondition();
//
//	private WriteBatch enqueue(WriteCommand writeCommand, boolean sync) throws IOException {
//
//		WriteBatch result = null;
//
//		this.enqueueLock.lock();
//
//		try {
//
//			// 如果没有启动，则先启动appender线程
//
//			this.startAppendThreadIfNessary();
//
//			if (this.nextWriteBatch == null) {
//
//				result = this.newWriteBatch(writeCommand);
//
//				this.empty.signalAll();
//
//			}
//
//			else {
//
//				if (this.nextWriteBatch.canAppend(writeCommand)) {
//
//					this.nextWriteBatch.append(writeCommand);
//
//					result = this.nextWriteBatch;
//
//				}
//
//				else {
//
//					while (this.nextWriteBatch != null) {
//
//						try {
//
//							this.notEmpty.await();  //
//
//						}
//
//						catch (InterruptedException e) {
//
//							Thread.currentThread().interrupt();  //
// 
//						}
//
//					}
//
//					result = this.newWriteBatch(writeCommand);
//
//					this.empty.signalAll();
//
//				}
//
//			}
//
//			if (!sync) {
//
//				InflyWriteData inflyWriteData = this.inflyWrites.get(writeCommand.bytesKey);
//
//				switch (writeCommand.opItem.op) {
//
//				case OpItem.OP_ADD:
//
//					if (inflyWriteData == null) {
//
//						this.inflyWrites.put(writeCommand.bytesKey, new InflyWriteData(writeCommand.data));
//
//					}
//
//					else {
//
//						// update and increase reference count;
//
//						inflyWriteData.data = writeCommand.data;
//
//						inflyWriteData.count++;
//
//					}
//
//					break;
//
//				case OpItem._OP_DEL_:
//
//					// 无条件删除
//
//					if (inflyWriteData != null) {
//
//						this.inflyWrites.remove(writeCommand.bytesKey);
//
//					}
//
//				}
//
//			}
//
//			return result;
//
//		}
//
//		finally {
//
//			this.enqueueLock.unlock();
//
//		}
//
//	}
//
//
//	/// version1
//	private WriteBatch enqueue_v1(WriteCommand writeCommand, boolean sync) throws IOException, 
//	InterruptedException { // 抛出
//
//		WriteBatch result = null;
//
//		this.enqueueLock.lock();
//
//		try {
//
//		    // 如果没有启动，则先启动appender线程
//
//		    this.startAppendThreadIfNessary();
//
//		    if (this.nextWriteBatch == null) {
//
//		        result = this.newWriteBatch(writeCommand);
//
//		        this.empty.signalAll();
//
//		    }
//
//		    else {
//
//		        if (this.nextWriteBatch.canAppend(writeCommand)) {
//
//		            this.nextWriteBatch.append(writeCommand);
//
//		            result = this.nextWriteBatch;
//
//		        }
//
//		        else {
//
//		            while (this.nextWriteBatch != null) {
//
//		                    this.notEmpty.await();
//
//		            }
//
//		            result = this.newWriteBatch(writeCommand);
//
//		            this.empty.signalAll();
//
//		        }
//
//		    }
//
//		    if (!sync) {
//
//		        InflyWriteData inflyWriteData = this.inflyWrites.get(writeCommand.bytesKey);
//
//		        switch (writeCommand.opItem.op) {
//
//		        case OpItem._OP_ADD_:
//
//		            if (inflyWriteData == null) {
//
//		                this.inflyWrites.put(writeCommand.bytesKey, new InflyWriteData(writeCommand.data));
//
//		            }
//
//		            else {
//
//		                // update and increase reference count;
//
//		                inflyWriteData.data = writeCommand.data;
//
//		                inflyWriteData.count++;
//
//		            }
//
//		            break;
//
//		        case OpItem._OP_DEL_:
//
//		            // 无条件删除
//
//		            if (inflyWriteData != null) {
//
//		                this.inflyWrites.remove(writeCommand.bytesKey);
//
//		            }
//
//		        }
//
//		    }
//
//		    return result;
//
//		}
//
//		finally {
//
//		    this.enqueueLock.unlock();
//
//		}
//
//	}
//	
//	///
//	public void processQueue() {
//
//	    while (true) {
//
//	        WriteBatch batch = null;
//
//	        this.enqueueLock.lock();
//
//	        try {
//
//	            while (true) {
//
//	                if (this.nextWriteBatch != null) {
//
//	                    batch = this.nextWriteBatch;
//
//	                    this.nextWriteBatch = null;
//
//	                    break;
//
//	                }
//
//	                if (this.shutdown) {
//
//	                    return;
//
//	                }
//
//	                try {
//
//	                    this.empty.await();
//
//	                }
//
//	                catch (InterruptedException e) {
//
//	                    break;
//
//	                }
//
//	            }  // end while
//
//	            this.notEmpty.signalAll();
//
//	        }
//
//	        finally {
//
//	            this.enqueueLock.unlock();
//
//	        }
//
//	        if (batch != null) {
//
//	            final DataFile dataFile = batch.dataFile;
//
//	            final LogFile logFile = batch.logFile;
//
//	            final List<WriteCommand> cmdList = batch.cmdList;
//
//	            try {
//
//	                this.writeDataAndLog(batch, dataFile, logFile, cmdList);
//
//	                this.processRemove(batch, dataFile, logFile);
//
//	            }
//
//	            finally {
//
//	                batch.latch.countDown();
//
//	            }
//
//	        }
//
//	    }
//
//	}
//}
