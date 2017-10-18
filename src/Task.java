
public class Task {
    private int id;
    private String description;
    private String tag;
    private String name;
    private String groupname;
    // Insert name && id && description into task tables
    // Insert groupName && id && taskId && playerId into task_membership
    Task(){};
    Task (String name,String description, String tag ) {
        this.description = description;
        this.tag = tag;
        this.name = name;
    }
    Task (String name,String description, String tag, String groupname) {
        this.description =  description;
        this.tag = tag;
        this.name = name;
        this.groupname = groupname;
    }
    Task (String name, String tag) {
        this.tag =  tag;
        this.name = name;
    }
    Task (int id, String name,String description, String tag ) {
        this.id = id;
        this.description = description;
        this.tag = tag;
        this.name = name;
    };

    public String getDescription() {
        return description;
    }

    public String getGroupname() {
        return groupname;
    }

    public int getId() {return id;};
    public String getName() {
        return name;
    }
    public String getTag() {
        return tag;
    }
    public void setName(String name){
        this.name = name ;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void changeTag(Task task, String tag) {
        task.tag = tag;
    }
}
