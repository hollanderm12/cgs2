package dao;

import java.util.List;
import model.Result;

public interface ResultDAO {
    public void addResult(Result s);
    public void updateResult(Result s);
    public List<Result> listResults();
    public Result getResultById(int id);
    public void removeResult(int id);    
}
