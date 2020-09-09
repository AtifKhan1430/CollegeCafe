package com.example.final1;

    public class Food {
        private int id;
        private String name;
        private String price;
        private String email;
        private byte[] image;
        static int CartQuantity=0;
        public Food(String name, String price, byte[] image,String email, int id) {
            this.name = name;
            this.price = price;
            this.image = image;
            this.email = email;
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        public byte[] getImage() {
            return image;
        }

        public void setImage(byte[] image) {
            this.image = image;
        }
    }