package cc.mrbird.febs.api.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.BookingInfoVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.exception.FebsException;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/booking")
@Api(value="预约 API",tags={"预约相关操作"})
public class BookingController {

    final static Integer BOOKING = 1;

    final static Integer NO_BOOKING = 0;

    @Log("老师预约情况")
    @GetMapping("/teacher/{id}")
    public Result add(@NotBlank(message = "{required}") @PathVariable Integer id) throws FebsException {
        BookingInfoVO vo = new BookingInfoVO();
        vo.setId(1);
        vo.setStartTime(Calendar.getInstance().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 3);
        vo.setEndTime(calendar.getTime());
        vo.setTeacherId(id);
        vo.setStatus(NO_BOOKING);
        vo.setPrice(300);
        BookingInfoVO vo1 = new BookingInfoVO();
        vo1.setId(2);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,3);
        vo1.setStartTime(calendar.getTime());
        calendar.add(Calendar.HOUR, 3);
        vo1.setEndTime(calendar.getTime());
        vo1.setTeacherId(id);
        vo1.setStatus(BOOKING);
        vo1.setPrice(900);
        List<BookingInfoVO> list = new ArrayList<>();
        list.add(vo);
        list.add(vo1);
        return ResultUtil.success(list);
    }

    @Log("我的预约情况")
    @GetMapping("/user/{id}")
    public Result userBooking(@NotBlank(message = "{required}") @PathVariable Integer id) throws FebsException {
        BookingInfoVO vo = new BookingInfoVO();
        vo.setId(1);
        vo.setStartTime(Calendar.getInstance().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 3);
        vo.setEndTime(calendar.getTime());
        vo.setTeacherId(1);
        vo.setStatus(BOOKING);
        vo.setPrice(300);
        BookingInfoVO vo1 = new BookingInfoVO();
        vo.setId(2);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,3);
        vo.setStartTime(calendar.getTime());
        calendar.add(Calendar.HOUR, 3);
        vo.setEndTime(calendar.getTime());
        vo.setTeacherId(1);
        vo.setStatus(BOOKING);
        vo.setPrice(900);
        List<BookingInfoVO> list = new ArrayList<>();
        list.add(vo);
        list.add(vo1);
        return ResultUtil.success(list);
    }

    @Log("预约老师")
    @PostMapping("/{id}")
    public Result booking(@NotBlank(message = "{required}") @PathVariable Integer id) throws FebsException {
        return ResultUtil.success();
    }
}
