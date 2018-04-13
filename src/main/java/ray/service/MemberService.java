package ray.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ray.repository.MemberDao;

import java.math.BigDecimal;

/**
 * Created by ChanPong on 2016-08-10.
 */
@Slf4j
@Service
public class MemberService {
//	@Autowired
//	@Setter
//	private MemberDao memberDao;

    /** 추후 기능으로 만들어 블로그 메뉴에 추가할것 */
//    public static void main(String[] args) {
//        Float exchangeRateAmt = 1066.80f;
//        Integer amt = 1500;
//        Integer orgPartCancelRemainAmt = 2500;
//
//        Float exchangePrice = amt / exchangeRateAmt;
//        exchangePrice = (float) (Math.round(exchangePrice * 100) / 100.0);
//        System.out.println("exchangePrice = " + exchangePrice);
//
//        Float partCancelRemainAmt = orgPartCancelRemainAmt / exchangeRateAmt;
//        partCancelRemainAmt = (float) (Math.round(partCancelRemainAmt * 100) / 100.0);
//        System.out.println("partCancelRemainAmt = " + partCancelRemainAmt);
//
//        BigDecimal p1 = new BigDecimal(Float.toString(exchangePrice));
//        BigDecimal p2 = new BigDecimal(Float.toString(partCancelRemainAmt));
//        String result = p1.add(p2).toString();
//
//        System.out.println("result balance = " + result);
//    }
}
