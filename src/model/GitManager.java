package model;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import com.hexidec.ekit.Ekit;

/**
 * Manages the Git services
 * 
 * @author Raphaël HASCOËT
 */
public class GitManager {
	/**
	 * Sends the post to the website's git repository. The post needs to be saved in the local website's posts folder in order for this to work
	 * 
	 * @param post The post to be sent to the Git repository
	 */
	public static void sendPost(Post post){
		// WITH CMD LINE
		Tools.executeCmd("git add .", Ekit.getTxtfieldGitrepo().getText());
		Tools.executeCmd("git commit -m \"Add markdown file\"",Ekit.getTxtfieldGitrepo().getText());
		Tools.executeCmd("git push", Ekit.getTxtfieldGitrepo().getText());

//		FileRepositoryBuilder repoBuilder = new FileRepositoryBuilder();
//		try {
//			// WITH GIT LIBRARY
//			String fileToAdd = WebsiteManager.getPostUrl(post).replace(WebsiteManager.getWebsitePath(), "");
//
//			Repository repo = repoBuilder.setGitDir(new File(GIT_REPO))
//					.readEnvironment()
//					.findGitDir()
//					.setMustExist(true)
//					.build();
//
//			Git git = new Git(repo);
//
//			git.add().addFilepattern(fileToAdd).call();
//
//			git.commit().setMessage("Added post " + post.getFileName()).call();
//
//			git.push().call();
//
//			git.close();
//		} catch (IOException e) {
//			ErrorManager.sendError("Error while reading file\n" + e);
//		} catch (NoFilepatternException e) {
//			ErrorManager.sendError("Error on the Git file pattern.\n" + e);
//		} catch (GitAPIException e) {
//			ErrorManager.sendError("Error with the Git API.\n" + e);
//		}
	}

}
