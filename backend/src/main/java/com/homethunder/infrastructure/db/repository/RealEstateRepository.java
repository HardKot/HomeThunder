package com.homethunder.infrastructure.db.repository;

import com.homethunder.infrastructure.db.schema.RealEstateSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "realEstate", path = "realEstate")
public interface RealEstateRepository extends JpaRepository<RealEstateSchema, UUID>, PagingAndSortingRepository<RealEstateSchema, UUID> {
}
