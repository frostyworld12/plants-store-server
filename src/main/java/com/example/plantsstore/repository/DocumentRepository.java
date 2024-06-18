package com.example.plantsstore.repository;
import org.springframework.data.domain.*;
import org.springframework.data.repository.*;
import com.example.plantsstore.model.*;

public interface DocumentRepository extends CrudRepository<Document, String> {

}
