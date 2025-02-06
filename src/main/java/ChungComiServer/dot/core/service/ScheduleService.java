package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.dto.schedule.ResponseScheduleDTO;
import ChungComiServer.dot.core.entity.Schedule;
import ChungComiServer.dot.core.enums.Month;
import ChungComiServer.dot.core.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<ResponseScheduleDTO> getAllSchedulesOfTheMonth(Month month) {
        int ordinalMonth = month.getMonthNumber();
        List<Schedule> schedules = scheduleRepository.getAllSchedulesOfTheMonth(ordinalMonth);
        return schedules.stream().map(ResponseScheduleDTO::new).toList();
    }
}
