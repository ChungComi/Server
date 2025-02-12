package ChungComiServer.dot.api.controller;

import ChungComiServer.dot.api.service.CafeteriaMealScraper;
import ChungComiServer.dot.api.service.DevEventScraper;
import ChungComiServer.dot.api.service.DormitoryMealScraper;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CrawlingAPIController {

    private final CafeteriaMealScraper  cafeteriaMealScraper;
    private final DormitoryMealScraper dormitoryMealScraper;
    private final DevEventScraper devEventScraper;
    @GetMapping("/cafeteria/{cafeteria}")
    public Response getCafeteriaMenu(@PathVariable String cafeteria) {
        try{
            Map<String, Object> cafeteriaMenu = cafeteriaMealScraper.getCafeteriaMenu(cafeteria);
            return Response.success(cafeteriaMenu);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("/dorm/{dormitory}")
    public Response getDormitoryMenu(@PathVariable String dormitory) {
        try {
            Map<String, Object> dormitoryMeal = dormitoryMealScraper.getDormitoryMeal(dormitory);
            return Response.success(dormitoryMeal);
        } catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("/dev-events")
    public Response getDevEvents() {
        try {
            Map<String, Object> devEvents = devEventScraper.getDevEvents();
            return Response.success(devEvents);
        } catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }
}
