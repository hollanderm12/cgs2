package service;

import java.util.List;
import model.Result;
import org.springframework.ui.Model;

public interface ResultService {
    public int addResult(Result r);
    public int updateResult(Result r);
    public List<Result> listResults();
    public Result getResultById(int id);
    public void removeResult(int id, Result r);
    public Model populateDropdowns(Model model);
    public Model lookupResult(Model model, String id);
}
