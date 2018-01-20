package bmtestv4.android.mohalim.bmtestv4;

/**
 * Created by Mohamed ALi on 12/29/2017.
 */

public class Link {
    String _title, _description;
    int _resource;

    public Link(String title, String description, int resource){
        _title = title;
        _description = description;
        _resource= resource;
    }

    public int get_resource() {
        return _resource;
    }

    public String get_title() {
        return _title;
    }

    public String get_description() {
        return _description;
    }
}
