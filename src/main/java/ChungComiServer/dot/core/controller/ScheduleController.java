package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.schedule.ModifyScheduleDTO;
import ChungComiServer.dot.core.dto.schedule.RegisterScheduleDTO;
import ChungComiServer.dot.core.dto.schedule.ResponseScheduleDTO;
import ChungComiServer.dot.core.enums.Month;
import ChungComiServer.dot.core.service.ScheduleService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.UserContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserContext userContext;

    @GetMapping("/{month}")
    public Response getAllSchedulesOfTheMonth(@PathVariable("month") Month month){
        try{
            List<ResponseScheduleDTO> responseScheduleDTOs = scheduleService.getAllSchedulesOfTheMonth(userContext.getUserId(), month);
            return Response.success(responseScheduleDTOs);
        } catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PostMapping("")
    public Response registerSchedule(@Valid @RequestBody RegisterScheduleDTO registerScheduleDTO, BindingResult result){
        try{
            if (result.hasErrors())
                return Response.failure(new ErrorCode(result.getFieldError().toString()));
            Long id = scheduleService.registerSchedule(userContext.getUserId(),registerScheduleDTO.getContent(),registerScheduleDTO.getDate());
            return Response.success(id);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PutMapping("{scheduleId}")
    public Response modifySchedule(@PathVariable String scheduleId, @RequestBody ModifyScheduleDTO modifyScheduleDTO){
        try{
            ResponseScheduleDTO schedule =
                    scheduleService.modifySchedule(userContext.getUserId(), scheduleId,modifyScheduleDTO.getContent(),modifyScheduleDTO.getDate());
            return Response.success(schedule);
        } catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

}
