//#ifndef GRAPH_H
//#define GRAPH_H
#include <unordered_map>
#include <vector>

using namespace std;

enum state {white, grey, black};
enum tags {M, R, O, NA};
enum term {E, S, F};

class Graph {
    public:
        struct Node {
            vector<Node*> prereqs;
            vector<Node*> postreqs;
            string name; //name of the course
            int choice; //if no need to choose, 0; else 1++
            tags tag; //whether it is mandatory, required, or optional
            string option; //additional credit characters
            term availability; //if it is available during fall, spring, or both
            int credits; //number of credits
            state color; //white, grey, or black
        };
        Graph();
        bool insert(string name, string tag, vector<string> prereqs);
        bool insertCourses(string name, string option, string availability, int credits);
        bool insertChoices(int key, int min, vector<string> choices);
        bool insertCredits(string option, int credits); //insert to addCredits
        bool checkCourse(string course, string semester);
        void checkError();
        bool setCredit(string course, int credits);
        bool setAvailability(string course, string avail);
        bool setOption(string course, string option);
        void print();
        void checkRequirements();
        //need find(string course)
    private:
        bool error;
        vector<string> optionChars;
        unordered_map<string, term> terms;
        unordered_map<string, tags> tagMap;
        unordered_map<int, int> choices;
        unordered_map<int, vector<string>> choiceCourses;
        unordered_map<string, int> addCredits; //key is credit char, value is # credits needed
        unordered_map<string, Node*> vertices; //key is the course name, value is Node*
        unordered_map<string, int> satisfiedCredits; //adds total of additional credits
};

