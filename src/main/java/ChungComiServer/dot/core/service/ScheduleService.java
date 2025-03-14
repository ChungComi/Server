package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.dto.schedule.ResponseIdScheduleDTO;
import ChungComiServer.dot.core.dto.schedule.ResponseScheduleDTO;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.entity.Schedule;
import ChungComiServer.dot.core.enums.Month;
import ChungComiServer.dot.core.repository.MemberRepository;
import ChungComiServer.dot.core.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public List<LocalDateTime> getAllSchedulesOfTheMonth(Long userId, int year, int month) {
        return scheduleRepository.getAllSchedulesOfTheMonth(userId, year, month);
    }

    @Transactional(readOnly = false)
    public Long registerSchedule(Long userId, String content, LocalDateTime date) {
        Member member = memberRepository.findById(userId);
        Schedule schedule = new Schedule(content,date);
        schedule.addMember(member);
        return scheduleRepository.registerSchedule(schedule);
    }

    @Transactional(readOnly = false)
    public ResponseScheduleDTO modifySchedule(Long userId, String stringScheduleId, String content, LocalDateTime date) throws InvalidPropertiesFormatException, IllegalAccessException {
        Long scheduleId = Long.valueOf(stringScheduleId);
        Schedule schedule = scheduleRepository.findById(scheduleId);
        schedule.modifySchedule(userId,content,date);
        return new ResponseScheduleDTO(schedule);
    }

    @Transactional(readOnly = false)
    public void deleteSchedule(Long userId, String stringScheduleId) throws IllegalAccessException {
        Long scheduleId = Long.valueOf(stringScheduleId);
        Schedule schedule = scheduleRepository.findById(scheduleId);
        if(!schedule.getMember().getId().equals(userId))
            throw new IllegalAccessException("일정 작성자만 삭제 가능합니다.");
        scheduleRepository.deleteSchedule(schedule);
    }

    public List<ResponseIdScheduleDTO> getSchedulesOfTheDate(Long userId, LocalDateTime date) {
        List<Schedule> schedules = scheduleRepository.findByDate(userId,date);
        return schedules.stream().map(ResponseIdScheduleDTO::new).toList();
    }
}
