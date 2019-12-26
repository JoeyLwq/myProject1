package dao;

import entity.common.CommonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommonDao<T extends CommonEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {
}
