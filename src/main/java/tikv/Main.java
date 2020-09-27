package tikv;

import org.tikv.common.TiConfiguration;
import org.tikv.common.TiSession;
import org.tikv.kvproto.Kvrpcpb;
import org.tikv.raw.RawKVClient;
import shade.com.google.protobuf.ByteString;

import java.nio.charset.Charset;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String args[]) {
        TiConfiguration conf = TiConfiguration.createRawDefault("172.16.22.140:2379,172.16.22.141:2379,172.16.22.142:2379");
        TiSession session = TiSession.create(conf);
        RawKVClient rawClient = session.createRawClient();

        /// put
        rawClient.put(ByteString.copyFrom("wei-test-key", Charset.defaultCharset()),
                ByteString.copyFrom("wei-test-value", Charset.defaultCharset())
                );

        /// get
        ByteString bytes = rawClient.get(ByteString.copyFrom("wei-test-key", Charset.defaultCharset()));
        String s = bytes.toStringUtf8();;
        System.out.println(s);

        /// scan
        List<Kvrpcpb.KvPair> scan = rawClient.scan(ByteString.copyFrom("wei-test-key", Charset.defaultCharset()), 100);
        ListIterator<Kvrpcpb.KvPair> kvPairListIterator = scan.listIterator();
        while(kvPairListIterator.hasNext()) {
            Kvrpcpb.KvPair next = kvPairListIterator.next();
            String key = next.getKey().toStringUtf8();
            String value = next.getValue().toStringUtf8();

            System.out.println(key);
            System.out.println(value);
        }

        /// delete
        rawClient.delete(ByteString.copyFrom("wei-test-key", Charset.defaultCharset()));
    }
}