package ch2.item02;

public class NutritionFacts {
    private final int servings;
    private final int servingSize;
    private final int sodium;
    private final int fat;
    private final int carbohydrate;

    public static class Builder{
        private final int servings;
        private final int servingSize;

        private int sodium =0 ;
        private int fat =0 ;
        private int carbohydrate =0;

        public Builder(int servings, int servingSize){
            this.servings = servings;
            this.servingSize = servingSize;
        }

        public Builder sodium(int val){
            sodium = val;
            return this;
        }
        public Builder fat(int val){
            sodium = val;
            return this;
        }
        public Builder carbohydrate(int val){
            sodium = val;
            return this;
        }
        public NutritionFacts build(){
            return new NutritionFacts(this);
        }

    }
    public NutritionFacts(Builder builder){
        servingSize = builder.servingSize;
        servings = builder.servings;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new Builder(10,100)
                .fat(200)
                .carbohydrate(300)
                .sodium(150)
                .build();
    }


}
