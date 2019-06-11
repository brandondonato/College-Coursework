#include "Node.h"

Node::Node(){}

Node::Node(int i, string s){
	this->indexNum = i;
	this->infoStr = s;
}

void Node::setIndex(int i){
	this->indexNum = i;
}

int Node::getIndex(){
	return this->indexNum;
}

void Node::setString(string s){
	this->infoStr = s;
}

string Node::getInfoStr(){
	return this->infoStr;
}
