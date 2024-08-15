package dev.dubrovsky.controller.group;

import dev.dubrovsky.service.group.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/group")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

}
