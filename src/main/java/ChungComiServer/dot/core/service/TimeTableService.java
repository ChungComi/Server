package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.dto.timetable.ResponseTimeTableDTO;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.entity.TimeTable;
import ChungComiServer.dot.core.enums.DayOfWeek;
import ChungComiServer.dot.core.repository.MemberRepository;
import ChungComiServer.dot.core.repository.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final MemberRepository memberRepository;

    public List<ResponseTimeTableDTO> findMyAllTimeTables(String stringUserId) {
        Long userId = Long.valueOf(stringUserId);
        List<TimeTable> timeTables =  timeTableRepository.findMyAllTimeTables(userId);
        return timeTables.stream().map(ResponseTimeTableDTO::new).toList();
    }

    public Long addClass(String stringUserId, String className, String professor, DayOfWeek dayOfWeek,
                         LocalDateTime startTime, LocalDateTime endTime) {
        Long userId = Long.valueOf(stringUserId);
        Member member = memberRepository.findById(userId);
        TimeTable timeTable = new TimeTable(className,professor,dayOfWeek,startTime,endTime);
        timeTable.addMember(member);
        return timeTableRepository.addClass(timeTable);
    }
}
