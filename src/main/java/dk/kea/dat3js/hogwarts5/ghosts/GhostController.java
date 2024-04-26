package dk.kea.dat3js.hogwarts5.ghosts;

import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.house.HouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ghosts")
public class GhostController {
    private final List<Ghost> ghosts = new ArrayList<>();
    private final HouseService houseService;
    public GhostController(HouseService houseService) {
        this.houseService = houseService;

    }
private void initGhosts(){
    if (ghosts.isEmpty()){
        ghosts.add(new Ghost(1, "Nearly Headless Nick", "Sir Nicholas de Mimsy-Porpington", houseService.findById("Gryffindor").get()));
        ghosts.add(new Ghost(2, "The Bloody Baron", "Baron", houseService.findById("Slytherin").get()));
        ghosts.add(new Ghost(3, "The Fat Friar", "Friar", houseService.findById("Hufflepuff").get()));
        ghosts.add(new Ghost(4, "The Grey Lady", "Helena Ravenclaw", houseService.findById("Ravenclaw").get()));
        ghosts.add(new Ghost(5, "Moaning Myrtle", "Myrtle Elizabeth Warren", houseService.findById("Ravenclaw").get()));
    }
}
    @GetMapping
    public List<Ghost> getAllGhosts() {
        initGhosts();
        return ghosts;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Ghost> getGhostByName(@PathVariable String name) {
        initGhosts();
        for (Ghost ghost : ghosts) {
            if (ghost.getName().equalsIgnoreCase(name)) {
                return ResponseEntity.ok(ghost);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
