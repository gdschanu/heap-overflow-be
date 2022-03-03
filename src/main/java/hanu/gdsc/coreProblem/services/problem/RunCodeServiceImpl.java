package hanu.gdsc.coreProblem.services.problem;

import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;

@Service
public class RunCodeServiceImpl implements RunCodeService {
    @Override
    public Output execute(String code, String input, ProgrammingLanguage programmingLanguage) {
        return null;
    }
}
