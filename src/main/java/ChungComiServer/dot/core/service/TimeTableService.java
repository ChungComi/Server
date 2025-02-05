package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.dto.timetable.ResponseTimeTableDTO;
import ChungComiServer.dot.core.entity.TimeTable;
import ChungComiServer.dot.core.repository.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;

    public List<ResponseTimeTableDTO> findMyAllTimeTables(String stringUserId) {
        Long userId = Long.valueOf(stringUserId);
        List<TimeTable> timeTables =  timeTableRepository.findMyAllTimeTables(userId);
        return timeTables.stream().map(ResponseTimeTableDTO::new).toList();
    }
}
