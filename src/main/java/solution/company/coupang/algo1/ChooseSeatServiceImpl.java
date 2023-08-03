package solution.company.coupang.algo1;

import java.util.Date;
import java.util.Set;

/**
 * Q: 实现一个电影票购票功能
 *
 * 	需求：
 * 		1. 需要支持选座位（可以一次购买多个座位）
 * 		2. 电影票需要包含，电影名称，放映厅，播放日期时间，以及座位信息
 * 		3. 请支持多人同时购票
 */
public class ChooseSeatServiceImpl implements ChooseSeatService {

    @Override
    synchronized public void chooseSeat(Ticket ticket) throws NoTicketException {
        if(ticket == null)  {
            throw  new NoTicketException(); // error code
        }

        /// from request
        String filmName = ticket.filmName;
        Date playDate = ticket.playDate;
        Cerama cerama = ticket.cerama;
        String cemeraName = cerama.cemera;
        Set<Seat> bookingSeats = cerama.bookingSeats;  // 用户预定的位置


        Set<Seat> allOfSeats = cerama.allOfSeats;

        int allOfAmount = allOfSeats.size();
        int bookingAmount = bookingSeats.size();


        if(bookingAmount <= allOfAmount) {
            allOfSeats.removeAll(bookingSeats); /// db
        } else {
            /// exceptins
        }
    }
}