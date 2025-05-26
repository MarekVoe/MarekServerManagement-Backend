package dev.marekvoe.marekservermanagement.controllers;

import dev.marekvoe.marekservermanagement.models.Node;
import dev.marekvoe.marekservermanagement.services.NodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/nodes")
public class NodeController {

    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping()
    public ResponseEntity<String> defaultMapping() {
        return ResponseEntity.ok("Node API: Use /all, /{id}, /create, etc.");
    }

    @GetMapping("/all")
    public List<Node> getAllNodes() {
        return nodeService.getAllNodes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Node> getNodeById(@PathVariable Long id) {
        return nodeService.getNodeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Node> createNode(@RequestBody Node node) {
        return ResponseEntity.ok(nodeService.createNode(node));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Node> updateNode(@PathVariable Long id, @RequestBody Node updatedNode) {
        return ResponseEntity.ok(nodeService.updateNode(id, updatedNode));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNode(@PathVariable Long id) {
        nodeService.deleteNode(id);
        return ResponseEntity.noContent().build();
    }
}
