package ChungComiServer.dot.api.controller;

import ChungComiServer.dot.core.dto.schedule.ResponseIdScheduleDTO;
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
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleAPIController {

    private final ScheduleService scheduleService;
    private final UserContext userContext;

    @GetMapping("{selectedDate}")
    public Response getSchedulesOfTheDate(@PathVariable("selectedDate") LocalDateTime date){
        try{
            log.info("@@@@@이거야?");
            List<ResponseIdScheduleDTO> responseIdScheduleDTOs = scheduleService.getSchedulesOfTheDate(userContext.getUserId(), date);
            return Response.success(responseIdScheduleDTOs);
        } catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }
}
