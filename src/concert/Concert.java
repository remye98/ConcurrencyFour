package concert;

import java.util.HashMap;

/**
 * Created by e_voe_000 on 10/30/2016.
 */
public class Concert {

    public enum SectionType {
        STANDING_PIT(false),
        NORTH,
        SOUTH,
        RESERVED_FLOOR,
        GENERAL_ADMISSION(false);

        private boolean hasSeats = true;

        SectionType() {

        }

        SectionType(boolean hasSeats) {
            this.hasSeats = hasSeats;
        }
    }

    private final HashMap<SectionType, Section> sections = new HashMap<>();

    public Concert() {

        for (SectionType sectionType : SectionType.values()) {
            Section section;
            if (sectionType.hasSeats) {
                section = new Section.SeatingSection(100, 100);
            } else {
                section = new Section.StandingSection(1000);
            }
            sections.put(sectionType, section);
        }
    }

    public Section getSection(SectionType sectionType) {
        return sections.get(sectionType);
    }
}
