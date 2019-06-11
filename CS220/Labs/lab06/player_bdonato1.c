static int nRocks=0;
static int nScissors=0;
static int nPaper=0;
//static int wins=0;
//static int losses=0;
//static int ties=0;
static int currentRd=0;

rps player_bdonato1(int round,rps *myhist,rps *opphist) {
	if(sizeof(myhist) == 0){
		nRocks +=1;
		currentRd +=1;
		return Rock;
	}

	if(sizeof(myhist) >= 2){
		if(myhist[currentRd-2] == Rock && opphist[currentRd-2] == Scissors){
			nRocks += 1;
			currentRd +=1;
			return Rock;
		}else if(myhist[currentRd-1] == Scissors && opphist[currentRd-1] == Paper){
			nScissors +=1;
			currentRd +=1;
			return Scissors;
		}else if(opphist[currentRd]==Rock){
			nRocks +=1;
			currentRd +=1;
			return Rock;
		}
	}

	if(currentRd <= 3){
		nPaper +=1;
		currentRd +=1;
		return Paper;
	}else if(currentRd > 8){
		nScissors +=1;
		currentRd +=1;
		return Scissors;
	}else{
		nRocks +=1;
		currentRd +=1;
		return Rock;
	}
	//return Rock;
	
}

register_player(player_bdonato1,"bdonato1");
