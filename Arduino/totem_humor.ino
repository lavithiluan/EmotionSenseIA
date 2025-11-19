// Joystick
const int PIN_JOY_X = A0;   // Eixo X (Horizontal - HOR)
const int PIN_JOY_Y = A1;   // Eixo Y (Vertical - VER)
const int PIN_JOY_SW = 2;   // Botão (Switch - SEL)

// LED RGB 
const int PIN_LED_R = 9;    // Vermelho 
const int PIN_LED_G = 10;   // Verde 
const int PIN_LED_B = 11;   // Azul 

// Buzzer
const int PIN_BUZZER = 8;

int humorAtual = 0; 

void setup() {
  Serial.begin(9600); 

  pinMode(PIN_LED_R, OUTPUT);
  pinMode(PIN_LED_G, OUTPUT);
  pinMode(PIN_LED_B, OUTPUT);
  pinMode(PIN_BUZZER, OUTPUT);

  pinMode(PIN_JOY_SW, INPUT_PULLUP);

  setCorLED(255, 255, 255);
  delay(300);
  setCorLED(0, 0, 0);
}


void loop() {

  // 1. LER OS SENSORES
  int valorX = analogRead(PIN_JOY_X);
  int valorY = analogRead(PIN_JOY_Y);
  int valorSW = digitalRead(PIN_JOY_SW);

  // 2. INTERPRETAR O HUMOR E ATUALIZAR O LED

  // Cima (Feliz)
  if (valorY > 800) {
    humorAtual = 1;
    setCorLED(0, 255, 0); // Verde
  }
  // Baixo (Triste)
  else if (valorY < 200) {
    humorAtual = 2;
    setCorLED(0, 0, 255); // Azul
  }
  // Direita (Ansioso)
  else if (valorX > 800) {
    humorAtual = 3;
    setCorLED(255, 0, 255); // Roxo (Vermelho + Azul)
  }
  // Esquerda (Raiva)
  else if (valorX < 200) {
    humorAtual = 4;
    setCorLED(255, 0, 0); // Vermelho Puro
  }
  else {
    humorAtual = 0;
    setCorLED(0, 0, 0); // Apagado (Centro)
  }

  // 3. VERIFICAR SE O BOTÃO FOI PRESSIONADO
  if (valorSW == LOW && humorAtual != 0) {

    // Toca o Bip de confirmação
    tone(PIN_BUZZER, 1000, 100);

    // Envia o humor para o PC via USB
    if (humorAtual == 1) {
      Serial.println("HUMOR:FELIZ");
    } else if (humorAtual == 2) {
      Serial.println("HUMOR:TRISTE");
    } else if (humorAtual == 3) {
      Serial.println("HUMOR:ANSIOSO");
    } else if (humorAtual == 4) {
      Serial.println("HUMOR:RAIVA");
    }
    
    piscarLED();
    delay(1000); 
  }
  
  delay(50); 
}


// --- FUNÇÕES AUXILIARES ---
void setCorLED(int r, int g, int b) {
  analogWrite(PIN_LED_R, r);
  analogWrite(PIN_LED_G, g);
  analogWrite(PIN_LED_B, b);
}

// Função para piscar o LED
void piscarLED() {
  setCorLED(0, 0, 0); // Desliga
  delay(100);
  setCorLED(255, 255, 255); // Branco
  delay(100);
  setCorLED(0, 0, 0);
  delay(100);
  setCorLED(255, 255, 255);
  delay(100);
}