package dev.dubrovsky.controller.group;

import dev.dubrovsky.service.group.GroupStandingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/group/standing")
@AllArgsConstructor
public class GroupStandingController {

    private final GroupStandingService groupStandingService;

}
