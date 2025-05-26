package dev.marekvoe.marekservermanagement.services;

import dev.marekvoe.marekservermanagement.models.Node;
import dev.marekvoe.marekservermanagement.repositories.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NodeService {

    private final NodeRepository nodeRepository;

    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public List<Node> getAllNodes() {
        return nodeRepository.findAll();
    }

    public Optional<Node> getNodeById(Long id) {
        return nodeRepository.findById(id);
    }

    public Node createNode(Node node) {
        if (nodeRepository.existsByName(node.getName())) {
            throw new IllegalArgumentException("Node with this name already exists");
        }
        // TODO: -------------- Implement actual server creation logic --------------
        // TODO: Create node (minecraft server) executable jar that will run server, and through ProcessBuilder run it
        // TODO: Get data from server console and parse it to web-server and display it in UI (React Application), Maybe use WebSockets for real-time updates,
        //  and use separate service and controller for that ?
        // TODO: Save path to that file to database for later use

         return nodeRepository.save(node);
    }

    public Node updateNode(Long id, Node updatedNode) {
        Node existingNode = nodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Node not found"));

        existingNode.setName(updatedNode.getName());
        existingNode.setStatus(updatedNode.getStatus());
        existingNode.setIpAddress(updatedNode.getIpAddress());
        existingNode.setPort(updatedNode.getPort());
        existingNode.setVersion(updatedNode.getVersion());
        existingNode.setCreatedAt(updatedNode.getCreatedAt());
        existingNode.setMaxPlayers(updatedNode.getMaxPlayers());
        existingNode.setAutoStart(updatedNode.isAutoStart());
        existingNode.setDescription(updatedNode.getDescription());

        return nodeRepository.save(existingNode);
    }

    public void deleteNode(Long id) {
        nodeRepository.deleteById(id);
    }
}
