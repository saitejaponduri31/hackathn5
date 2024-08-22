import java.util.*;

public class ElectronicVotingSystem {
    
    // User class
    static class User {
        private String id;
        private String name;
        private boolean hasVoted;

        public User(String name) {
            this.id = UUID.randomUUID().toString();
            this.name = name;
            this.hasVoted = false;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public boolean hasVoted() {
            return hasVoted;
        }

        public void setHasVoted(boolean hasVoted) {
            this.hasVoted = hasVoted;
        }
    }

    // Candidate class
    static class Candidate {
        private String id;
        private String name;
        private int voteCount;

        public Candidate(String name) {
            this.id = UUID.randomUUID().toString();
            this.name = name;
            this.voteCount = 0;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public void incrementVoteCount() {
            this.voteCount++;
        }
    }

    // Voting system logic
    static class VotingSystem {
        private Map<String, User> users;
        private Map<String, Candidate> candidates;

        public VotingSystem() {
            this.users = new HashMap<>();
            this.candidates = new HashMap<>();
        }

        // Register a user
        public void registerUser(String name) {
            User user = new User(name);
            users.put(user.getId(), user);
            System.out.println("User registered: " + name + " (ID: " + user.getId() + ")");
        }

        // Add a candidate
        public void addCandidate(String name) {
            Candidate candidate = new Candidate(name);
            candidates.put(candidate.getId(), candidate);
            System.out.println("Candidate added: " + name + " (ID: " + candidate.getId() + ")");
        }

        // Vote for a candidate
        public void vote(String userId, String candidateId) {
            User user = users.get(userId);
            Candidate candidate = candidates.get(candidateId);

            if (user == null) {
                System.out.println("User not found.");
                return;
            }
            if (candidate == null) {
                System.out.println("Candidate not found.");
                return;
            }
            if (user.hasVoted()) {
                System.out.println("User has already voted.");
                return;
            }

            candidate.incrementVoteCount();
            user.setHasVoted(true);
            System.out.println(user.getName() + " voted for " + candidate.getName());
        }

        // Display results
        public void displayResults() {
            System.out.println("Voting Results:");
            for (Candidate candidate : candidates.values()) {
                System.out.println(candidate.getName() + ": " + candidate.getVoteCount() + " votes");
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        VotingSystem votingSystem = new VotingSystem();
    
        // Register users and capture their IDs
        votingSystem.registerUser("Alice");
        String aliceId = votingSystem.users.entrySet().stream().filter(entry -> entry.getValue().getName().equals("Alice")).map(Map.Entry::getKey).findFirst().orElse(null);
    
        votingSystem.registerUser("Bob");
        String bobId = votingSystem.users.entrySet().stream().filter(entry -> entry.getValue().getName().equals("Bob")).map(Map.Entry::getKey).findFirst().orElse(null);
    
        votingSystem.registerUser("Charlie");
        String charlieId = votingSystem.users.entrySet().stream().filter(entry -> entry.getValue().getName().equals("Charlie")).map(Map.Entry::getKey).findFirst().orElse(null);
    
        // Add candidates and capture their IDs
        votingSystem.addCandidate("Candidate 1");
        String candidate1Id = votingSystem.candidates.entrySet().stream().filter(entry -> entry.getValue().getName().equals("Candidate 1")).map(Map.Entry::getKey).findFirst().orElse(null);
    
        votingSystem.addCandidate("Candidate 2");
        String candidate2Id = votingSystem.candidates.entrySet().stream().filter(entry -> entry.getValue().getName().equals("Candidate 2")).map(Map.Entry::getKey).findFirst().orElse(null);
    
        // Users voting
        votingSystem.vote(aliceId, candidate1Id);
        votingSystem.vote(bobId, candidate2Id);
        votingSystem.vote(charlieId, candidate1Id);
    
        // Display results
        votingSystem.displayResults();
    }
    
   
}
