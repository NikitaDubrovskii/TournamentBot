package dev.dubrovsky.service.group;

import dev.dubrovsky.repository.group.GroupMatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GroupMatchService {

    private final GroupMatchRepository groupMatchRepository;

}
