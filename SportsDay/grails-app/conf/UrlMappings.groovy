class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		//"/"(view:"/index")
		"/"(view:"/master")
		//"/bootstrap"(view:"/bootstrap")
		"500"(view:'/error')
	}
}
