package com.operationcredit.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.operationcredit.app.models.TypeOperation;

public interface ITypeOperationRepo extends ReactiveMongoRepository<TypeOperation, String>{

}
