package io.pivotal.pal.tracker;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    TimeEntryRepository repo;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repo = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity(repo.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @PutMapping("/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry temp  =  repo.update(timeEntryId, expected);
        if(temp != null){
            return new ResponseEntity<TimeEntry>(temp, HttpStatus.OK);
        } else{
            return new ResponseEntity<TimeEntry>(temp, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{timeEntryId}")

    public ResponseEntity delete(@PathVariable long timeEntryId) {
        repo.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(repo.list(), HttpStatus.OK);
    }

    @GetMapping("/{nonExistentTimeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long nonExistentTimeEntryId) {
        TimeEntry temp = repo.find(nonExistentTimeEntryId);
        if(temp != null){
            return new ResponseEntity<TimeEntry>(temp, HttpStatus.OK);
        } else{
            return new ResponseEntity<TimeEntry>(temp, HttpStatus.NOT_FOUND);
        }
    }
}
