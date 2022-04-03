package com.jpm.books.library.response;

public class RestMessageResponse {
        private String message;

        public RestMessageResponse(String message){
            this.message = message;
        }

        public void setMessage(String message){
            this.message = message;
        }

        public String getMessage(){
            return message;
        }
}
