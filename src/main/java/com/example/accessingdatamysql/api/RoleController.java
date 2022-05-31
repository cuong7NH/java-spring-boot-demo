package com.example.accessingdatamysql.api;

import com.example.accessingdatamysql.entity.Role;
import com.example.accessingdatamysql.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    // get all Roles
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.findAll();
    }

//    public ResponseEntity<PagingResponse<ArmyModelDetailDTO>> getGModels(ArmyModelRequest req) {
//        var page = service.findGModels(req);
//        var rs = new PagingResponse<ArmyModelDetailDTO>()
//                .setTotal((int) page.getTotalElements())
//                .setData(armyAdminMapper.toArmyDetailDTOs(page.getContent()));
//        return ResponseEntity.ok(rs);
//    }

}
