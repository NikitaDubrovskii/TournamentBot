package dev.dubrovsky.service.group;

import dev.dubrovsky.repository.group.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

}
