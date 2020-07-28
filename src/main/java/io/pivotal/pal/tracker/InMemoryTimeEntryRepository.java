package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    HashMap<Long, TimeEntry> repo = new HashMap<Long, TimeEntry>();
    Long count = 0L;
    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(++count);
        repo.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {

        return repo.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(repo.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry temp = repo.get(id);
        if(temp!=null){
            timeEntry.setId(id);
            repo.put(timeEntry.getId(), timeEntry);
            temp = timeEntry;
        }
        return temp;
    }

    @Override
    public void delete(Long id) {
        repo.remove(id);
    }
}
