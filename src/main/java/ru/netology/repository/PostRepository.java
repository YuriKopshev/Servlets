package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

// Stub
public class PostRepository {
    private final CopyOnWriteArrayList<Post> postList = new CopyOnWriteArrayList<>();
    private int id = 1;


    public CopyOnWriteArrayList<Post> all() {
        return postList;
    }

    public Optional<Post> getById(long id) {
        Post result = null;
        for (Post post : postList) {
            if (post.getId() == id) {
                result = post;
            }
        }
        if (result == null) throw new AssertionError();
        return Optional.of(result);
    }

    public Post save(Post post) {
        long currentId = post.getId();
        if (post.getId() == 0) {
            postList.add(new Post(id, post.getContent()));
            id++;

        }
        if (post.getId() != 0) {
            boolean check = false;
            for (Post variable : postList) {
                if (variable.getId() == currentId) {
                    variable.setId(currentId);
                    variable.setContent(post.getContent());
                    check = true;
                }
            }
            if (!check) {
                postList.add(new Post(post.getId(), post.getContent()));
            }
        }
        return post;
    }

    public void removeById(long id) {
        postList.removeIf(post -> post.getId() == id);
    }
}
