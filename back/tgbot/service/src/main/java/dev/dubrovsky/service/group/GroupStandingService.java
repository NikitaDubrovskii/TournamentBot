package dev.dubrovsky.service.group;

import dev.dubrovsky.repository.group.GroupStandingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GroupStandingService {

    private final GroupStandingRepository groupStandingRepository;

}
