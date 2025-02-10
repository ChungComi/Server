package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.dto.timetable.ModifyTimeTableDTO;
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
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final MemberRepository memberRepository;

    public List<ResponseTimeTableDTO> findMyAllTimeTables(Long userId) {
        List<TimeTable> timeTables =  timeTableRepository.findMyAllTimeTables(userId);
        return timeTables.stream().map(ResponseTimeTableDTO::new).toList();
    }

    @Transactional(readOnly = false)
    public Long addClass(Long userId, String className, String professor, DayOfWeek dayOfWeek,
                         LocalDateTime startTime, LocalDateTime endTime) {
        Member member = memberRepository.findById(userId);
        TimeTable timeTable = new TimeTable(className,professor,dayOfWeek,startTime,endTime);
        timeTable.addMember(member);
        return timeTableRepository.addClass(timeTable);
    }

    @Transactional(readOnly = false)
    public ResponseTimeTableDTO modifyTimeTable(Long userId, String stringTimeTableId, String className, String professor,
                                                DayOfWeek dayOfWeek, LocalDateTime startTime, LocalDateTime endTime ) throws InvalidPropertiesFormatException, IllegalAccessException {
        Long timeTableId = Long.valueOf(stringTimeTableId);
        TimeTable timeTable = timeTableRepository.findById(timeTableId);
        timeTable.modifyTimeTable(userId,className,professor,dayOfWeek,startTime,endTime);
        return new ResponseTimeTableDTO(timeTable);
    }

    @Transactional(readOnly = false)
    public void deleteTimeTable(Long userId, String stringTimeTableId) throws IllegalAccessException {
        Long timeTableId = Long.valueOf(stringTimeTableId);
        if(!timeTableRepository.findById(timeTableId).getMember().getId().equals(userId))
            throw new IllegalAccessException("일정 작성자만 삭제 가능합니다.");
        timeTableRepository.delete(timeTableId);
    }
}
