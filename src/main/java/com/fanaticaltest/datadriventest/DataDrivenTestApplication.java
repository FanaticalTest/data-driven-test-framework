package com.fanaticaltest.datadriventest;

import com.fanaticaltest.datadriventest.models.*;
import com.fanaticaltest.datadriventest.repositories.ModuleParametersRepo;
import com.fanaticaltest.datadriventest.repositories.ModuleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static com.fanaticaltest.datadriventest.data.initialLoad.generateDemoFeature;

@SpringBootApplication
public class DataDrivenTestApplication {

    private static final Logger log = LoggerFactory.getLogger(DataDrivenTestApplication.class);

    public static void main(String[] args) {
		SpringApplication.run(DataDrivenTestApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ModuleRepo moduleRepo, ModuleParametersRepo moduleParametersRepo) {
		return (args) -> {

			/*Feature feature = new Feature();
			feature = generateDemoFeature();
			List<Scenario> scenarios= new ArrayList<>();
			scenarios = feature.getScenarios();
			List<Step> steps = new ArrayList<>();
			List<Module> modules = new ArrayList<>();
			List<ModuleParameters> moduleParameters = new ArrayList<>();

			for(Scenario sc: scenarios )
			{
                steps = sc.getSteps();
			    for(Step st :steps)
			    {
			        modules = st.getModules();
			        for(Module m :modules)
                    {
                        moduleParameters = m.getModuleParameter();
                        moduleRepo.save(m);
                        log.info(m.toString());
                        for(ModuleParameters mp: moduleParameters)
                        {
                            mp.setModule(m);
                            moduleParametersRepo.save(mp);
                            log.info(mp.toString());
                        }
                        moduleParameters.clear();
                    }
                    modules.clear();
                }
                steps.clear();
			}*/


			Module module = new Module("name","snippet");
			ArrayList<ModuleParameters> parametersArrayList = new ArrayList<ModuleParameters>();

			parametersArrayList.add(new ModuleParameters("testId","101.1"));
			parametersArrayList.add(new ModuleParameters("browser","firefox"));

			module.setModuleParameter(parametersArrayList);

			moduleRepo.save(module);

			for (ModuleParameters moduleParameters : parametersArrayList)
			{
				moduleParameters.setModule(module);
				moduleParametersRepo.save(moduleParameters);
			}
		};
	}
}
