package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.timetable.ModifyTimeTableDTO;
import ChungComiServer.dot.core.dto.timetable.RegisterTimeTableDTO;
import ChungComiServer.dot.core.dto.timetable.ResponseTimeTableDTO;
import ChungComiServer.dot.core.service.TimeTableService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.UserContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/time-table")
public class TimeTableController {

    private final TimeTableService timeTableService;
    private final UserContext userContext;

    @GetMapping("")
    public Response getMyTimeTables(){
        try {
            String userId = userContext.getUserId();
            List<ResponseTimeTableDTO> timeTableDTOs = timeTableService.findMyAllTimeTables(userId);
            return Response.success(timeTableDTOs);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PostMapping("")
    public Response addClass(@Valid @RequestBody RegisterTimeTableDTO registerTimeTableDTO, BindingResult result){
        try {
            if(result.hasErrors())
                return Response.failure(new ErrorCode(result.getFieldError().toString()));
            String userId = userContext.getUserId();
            Long id = timeTableService.addClass(userId, registerTimeTableDTO.getClassName(),registerTimeTableDTO.getProfessor(),
                    registerTimeTableDTO.getDayOfWeek(),registerTimeTableDTO.getStartTime(),registerTimeTableDTO.getEndTime());
            return Response.success(id);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PutMapping("{timeTableId}")
    public Response modifyTimeTable(@PathVariable("timeTableId")String timeTableId, @RequestBody ModifyTimeTableDTO modifyTimeTableDTO){
        try{
            ResponseTimeTableDTO responseTimeTableDTO =
                    timeTableService.modifyTimeTable(timeTableId, modifyTimeTableDTO.getClassName(),modifyTimeTableDTO.getProfessor(),
                            modifyTimeTableDTO.getDayOfWeek(),modifyTimeTableDTO.getStartTime(),modifyTimeTableDTO.getEndTime());
            return Response.success(responseTimeTableDTO);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @DeleteMapping("{timeTableId}")
    public Response deleteTimeTable(@PathVariable("timeTableId")String timeTableId){
        try {
            timeTableService.deleteTimeTable(timeTableId);
            return Response.success();
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

}
