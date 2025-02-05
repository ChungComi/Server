package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.timetable.ResponseTimeTableDTO;
import ChungComiServer.dot.core.service.TimeTableService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
