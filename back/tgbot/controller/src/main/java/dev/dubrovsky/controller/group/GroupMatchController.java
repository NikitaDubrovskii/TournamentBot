package dev.dubrovsky.controller.group;

import dev.dubrovsky.service.group.GroupMatchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/group/match")
@AllArgsConstructor
public class GroupMatchController {

    private final GroupMatchService groupMatchService;

}
