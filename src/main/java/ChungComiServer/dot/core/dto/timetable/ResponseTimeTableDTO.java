package ChungComiServer.dot.core.dto.timetable;

import ChungComiServer.dot.core.entity.TimeTable;
import ChungComiServer.dot.core.enums.DayOfWeek;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ResponseTimeTableDTO {
    private String className;
    private String professor;
    private DayOfWeek dayOfWeek;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ResponseTimeTableDTO(TimeTable timeTable) {
        this.className = timeTable.getClassName();
        this.professor = timeTable.getProfessor();
        this.dayOfWeek = timeTable.getDayOfWeek();
        this.startTime = timeTable.getStartTime();
        this.endTime = timeTable.getEndTime();
    }
}
