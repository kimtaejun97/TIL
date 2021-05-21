package domain.busline;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "BUSLINE")
public class BusLine {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name="NAME")
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    private BusLine(Builder builder){
        id = builder.id;
        name = builder.name;

    }
    public static class Builder{
        private Integer id;
        private String name;

        public Builder(){}

        public Builder id(Integer val){
            id = val;
            return this;
        }

        public Builder name(String val){
            name =val;
            return this;
        }

        public BusLine build(){
            return new BusLine(this);
        }

    }
}


