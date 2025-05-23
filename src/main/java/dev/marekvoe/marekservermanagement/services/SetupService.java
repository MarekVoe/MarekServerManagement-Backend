package dev.marekvoe.marekservermanagement.services;

import dev.marekvoe.marekservermanagement.models.Setting;
import dev.marekvoe.marekservermanagement.repositories.SettingRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class SetupService {
    private final SettingRepository repo;
    private final DataSource adminDataSource;

    public SetupService(SettingRepository repo, DataSource adminDataSource) {
        this.repo = repo;
        this.adminDataSource = adminDataSource;
    }

    public boolean isSetupComplete() {
        return repo.findByKey("setup_complete")
                .map(s -> s.getValue().equalsIgnoreCase("true"))
                .orElse(false);
    }

    public void createDatabase(String dbName) throws SQLException {
        try (Connection conn = adminDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
            repo.save(new Setting("db_name", dbName));
        }
    }

    public void markSetupComplete() {
        repo.save(new Setting("setup_complete", "true"));
    }
}
