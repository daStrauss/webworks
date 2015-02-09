from flask import Flask
app = Flask(__name__)
import time

@app.route("/ping")
def hello():
    time.sleep(0.5)
    return "pong!"

if __name__ == "__main__":
    app.run()