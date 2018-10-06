package com.fanaticaltest.datadriventest.repositories;

import com.fanaticaltest.datadriventest.models.Module;
import org.springframework.data.repository.CrudRepository;

public interface ModuleRepo extends CrudRepository<Module, Long>{
}
