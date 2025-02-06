package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.schedule.ResponseScheduleDTO;
import ChungComiServer.dot.core.enums.Month;
import ChungComiServer.dot.core.service.ScheduleService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{month}")
    public Response getAllSchedulesOfTheMonth(@PathVariable("month") Month month){
        try{
            List<ResponseScheduleDTO> responseScheduleDTOs = scheduleService.getAllSchedulesOfTheMonth(month);
            return Response.success(responseScheduleDTOs);
        } catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

}
