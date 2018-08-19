package service;

import java.util.List;
import model.Result;
import org.springframework.web.servlet.ModelAndView;

public interface ResultService {
    public boolean addResult(Result r);
    public boolean updateResult(Result r);
    public List<Result> listResults();
    public Result getResultById(int id);
    public void removeResult(int id, Result r);
    public ModelAndView populateDropdowns(ModelAndView model);
    public ModelAndView lookupResult(ModelAndView model, String id);
}
