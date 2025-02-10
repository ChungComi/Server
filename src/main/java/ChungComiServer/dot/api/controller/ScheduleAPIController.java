package ChungComiServer.dot.api.controller;

import ChungComiServer.dot.core.dto.schedule.ResponseScheduleDTO;
import ChungComiServer.dot.core.enums.Month;
import ChungComiServer.dot.core.service.ScheduleService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleAPIController {

    private final ScheduleService scheduleService;
    private final UserContext userContext;

    @GetMapping("")
    public Response getSchedulesOfTheDate(LocalDateTime date){
        try{
            List<ResponseScheduleDTO> responseScheduleDTOs = scheduleService.getSchedulesOfTheDate(userContext.getUserId(), date);
            return Response.success(responseScheduleDTOs);
        } catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

}
